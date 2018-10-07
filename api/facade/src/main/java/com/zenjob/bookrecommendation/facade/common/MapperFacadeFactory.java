package com.zenjob.bookrecommendation.facade.common;

import com.zenjob.bookrecommendation.facade.common.mapping.MappingConfigurer;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MapperFacadeFactory extends AbstractFactoryBean<MapperFacade> {

    private final DefaultMapperFactory mapperFactory;

    //region Constructors
    @Autowired
    public MapperFacadeFactory(final List<MappingConfigurer> configurers) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        for (final MappingConfigurer configurer : configurers) {
            configurer.configure(mapperFactory);
        }
        logger.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    protected MapperFacade createInstance() {
        return mapperFactory.getMapperFacade();
    }
}