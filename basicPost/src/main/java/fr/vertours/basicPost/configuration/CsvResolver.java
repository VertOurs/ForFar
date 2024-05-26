package fr.vertours.basicPost.configuration;

import fr.vertours.basicPost.Utils.CsvUtils;
import fr.vertours.basicPost.annotation.CsvToObjects;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collections;


@Slf4j
public class CsvResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CsvToObjects.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        CsvToObjects annotation = parameter.getParameterAnnotation(CsvToObjects.class);
        if (annotation != null) {
            Class<?> keyClass = annotation.keyClass();
            log.info("Convert the csv to a list of : {}", keyClass.getSimpleName());
            return CsvUtils.csvToObjects(request.getInputStream().readAllBytes(), keyClass);
        } else {
            return Collections.emptyList();
        }
    }
}
