package com.tdonuk.sepetim.security;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.util.text.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.tdonuk.sepetim.constant.ContextParams.LOGGED_USER;
import static com.tdonuk.sepetim.constant.ContextParams.LOGGED_USERNAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Context {
    public static Object get(String name) {
        return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void set(String name, Object value) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static String loggedEmail() throws Exception {
        String loggedUser = (String) get(LOGGED_USERNAME);

        if(StringUtils.isBlank(loggedUser)) throw new SystemException("Bilinmeyen bir hata oluştu", "Bilinmeyen bir hata oluştu: Daha sonra tekrar deneyiniz");

        return loggedUser;
    }

    public static UserDTO loggedUser() throws Exception {
        UserDTO loggedUser = (UserDTO) get(LOGGED_USER);

        return loggedUser;
    }
}
