
package org.example.trafficmanageadmin.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.User;
import org.example.trafficmanageadmin.service.UserService;
import org.example.trafficmanageadmin.utils.JwtUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;

    public JwtAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


        final String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                String username = JwtUtils.getUsernameFromToken(token);

                if (username!= null) {
                    User userDetails = userService.getOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<User>().eq("username", username));

                    if (userDetails != null && JwtUtils.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, null);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将认证对象设置到安全上下文中
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        // 将用户信息存储在request属性中，供后续使用
                        request.setAttribute("user", userDetails);
                    } else {
                        log.warn("Token验证失败");
                    }
                }
            } catch (Exception e) {
                log.error("Token处理过程中出现异常: {}", e.getMessage(), e);
            }
        } else {
            log.warn("未找到有效的Authorization header");
        }
        filterChain.doFilter(request, response);
    }
}