package com.kreuterkeule.authvuejsapp.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    @Value("${application.security.access-token-secret}")
    public static final String JWT_ACCESS_SECRET = "lasdjfhgap;sdjfgnlkasdjfhb;sodfjhkg;aldjfnbsd;iofjhsodifhjgjs[odirjfhso;difjg[asdoifjhs[a'odihjg[sod'ifrhjs[odifhjg[aso'edjg[oadoifuhja['drihjua[sdofighsd[orig";
    @Value("${application.security.refresh-token-secret}")
    public static final String JWT_REFRESH_SECRET = "lasdjfhgap;sdjfgnlkasdjfhb;sodfjhkg;aldjfnbsd;iofjhsodifhjgjs[odirjfhso;difjg[asdoifjhs[a'odihjg[sod'ifrhjs[odifhjg[aso'edjg[oadoifuhja['drihjua[sdofighsd[orig";

}
