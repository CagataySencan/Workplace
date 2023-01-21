package com.tdonuk.sepetim.security;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.util.text.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.tdonuk.sepetim.constant.ContextParams.LOGGED_USER;
import static com.tdonuk.sepetim.constant.ContextParams.LOGGED_USERNAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Context {
    public static Object getAttr(String name) {
        return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttr(String name, Object value) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static String loggedEmail() throws Exception {
        String loggedUser = (String) getAttr(LOGGED_USERNAME);

        if(StringUtils.isBlank(loggedUser)) throw new Exception("Lütfen giriş yapınız");

        return loggedUser;
    }

    public static UserDTO loggedUser() throws Exception {
        UserDTO loggedUser = (UserDTO) getAttr(LOGGED_USER);

        return loggedUser;
    }
}
