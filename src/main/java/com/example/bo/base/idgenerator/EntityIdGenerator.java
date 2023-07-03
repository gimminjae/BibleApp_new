package com.example.bo.base.idgenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class EntityIdGenerator extends TableGenerator {
    public static final String DATE_FORMAT_PARAMETER = "dateFormat";
    public static final String DATE_FORMAT_DEFAULT = "%ty%tm%td";
    private String dateFormat;

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%d";
    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        if ("M".equals(valuePrefix)) {

            // 상품, 단품 채번의 경우에는 앞에 MALL식별자2자리가 포함되는데 이는 어떻게 넣어야할지 의논
            return valuePrefix + String.format(numberFormat, super.generate(session, object));
        } else {
            // 테스트용으로 원산지는 Date가 포함된 채번규칙 ( 주문, 쿠폰 등 채번규칙) 으로 생성하도록 임시 설정
            if (!dateFormat.contains("%1$")) {
                dateFormat = dateFormat.replace("%", "%1$");
            }
            if (!numberFormat.contains("%2$")) {
                numberFormat = numberFormat.replace("%", "%2$");
            }
            return valuePrefix
                    + String.format(dateFormat + numberFormat, LocalDateTime.now(), super.generate(session, object));
        }
    }

    @Override
    public void configure(Type type, Properties params,
            ServiceRegistry serviceRegistry) throws MappingException {
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
        dateFormat = ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT);
    }
}
