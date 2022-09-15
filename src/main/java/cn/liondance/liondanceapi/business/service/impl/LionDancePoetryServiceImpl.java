package cn.liondance.liondanceapi.business.service.impl;

import cn.liondance.liondanceapi.business.entity.LionDancePoetry;
import cn.liondance.liondanceapi.business.repository.LionDancePoetryRepository;
import cn.liondance.liondanceapi.business.service.LionDancePoetryService;
import cn.liondance.liondanceapi.utils.LionDanceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sunwei
 */
@Slf4j
@Service
@AllArgsConstructor
public class LionDancePoetryServiceImpl implements LionDancePoetryService {
    private final LionDancePoetryRepository lionDnacePoetryRepository;

    @Override
    public LionDancePoetry newPoetry(LionDancePoetry entity) {
        entity.setId(LionDanceIdGenerator.randomBase64UUID());
        return lionDnacePoetryRepository.saveAndFlush(entity);
    }
}
