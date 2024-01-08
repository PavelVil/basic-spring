package com.github.pavelvil.spring.core.custom_scope.config;

import com.github.pavelvil.spring.core.custom_scope.bean.StateBean;
import com.github.pavelvil.spring.core.custom_scope.bean.TemporalBean;
import com.github.pavelvil.spring.core.custom_scope.scope.SnapshotRestoreScope;
import com.github.pavelvil.spring.core.custom_scope.scope.TemporalScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.custom_scope")
public class ApplicationConfig {

    @Bean
    @Scope(TemporalScope.SCOPE_NAME)
    public TemporalBean temporalBean() {
        return new TemporalBean();
    }

    @Bean
    @Scope(SnapshotRestoreScope.SCOPE_NAME)
    public StateBean stateBean() {
        return new StateBean("First value");
    }

}
