package com.zhu.api_gateway.authen;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhu.api_gateway.entity.Permission;
import com.zhu.api_gateway.entity.RequestPath;
import com.zhu.api_gateway.feign.PermissionService;
import com.zhu.api_gateway.feign.RequestPathService;
import com.zhu.api_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "token";

    @Autowired
    PermissionService permissionService;

    @Autowired
    RequestPathService requestPathService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("全局过滤器!");
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 如果是登录请求则放行
//        if (request.getURI().getPath().contains("/admin/login")) {
//            return chain.filter(exchange);
//        }

        List<RequestPath> requestPaths = requestPathService.selectAll();
        RequestPath rp = null;
        for (RequestPath requestPath: requestPaths){
            if(request.getURI().getPath().contains(requestPath.getUrl())){
                rp = requestPath;
                break;
            }
        }

        if(rp == null){
            return chain.filter(exchange);
        }

        //4. 获取请求头
        HttpHeaders headers = request.getHeaders();
        //5. 请求头中获取令牌
        String token = headers.getFirst(AUTHORIZE_TOKEN);

        //6. 判断请求头中是否有令牌
        if (StringUtils.isEmpty(token)) {
            //7. 响应中放入返回的状态吗, 没有权限访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //8. 返回
            return response.setComplete();
        }

        //9. 如果请求头中有令牌则解析令牌
        try {
            Claims claims = JwtUtil.parseJWT(token);
            JSONObject jsonObject = JSON.parseObject(claims.getSubject());
            List<Permission> permissions = permissionService.selectByUser((Integer) jsonObject.get("id"));
            // 通过权限地址和现访问地址匹配判断是否授权
            boolean flag = false;
            for (Permission permission:permissions){
                List<RequestPath> byPermissionPaths = requestPathService.selectByPermission(permission.getId());
                for (RequestPath r:byPermissionPaths){
                    if(r.equals(byPermissionPaths)) {
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //11. 返回
            return response.setComplete();
        }
        //12. 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

