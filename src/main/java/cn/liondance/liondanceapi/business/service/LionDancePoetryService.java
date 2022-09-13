package cn.liondance.liondanceapi.business.service;

import cn.liondance.liondanceapi.business.entity.LionDancePoetry;

/**
 * The interface Lion dance book service.
 */
public interface LionDancePoetryService {
    /**
     * New book lion dance book.
     *
     * @param book the book
     * @return the lion dance book
     */
    LionDancePoetry newPoetry(LionDancePoetry book);
}
