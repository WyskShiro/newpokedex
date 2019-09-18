package com.ufms.mediadorpedagogico.domain.interactor.homework

import com.tem.domain.boundary.HomeworkRepository
import com.ufms.mediadorpedagogico.domain.entity.homework.HomeworkContent
import io.reactivex.Single

class GetHomework constructor(
    private val repository: HomeworkRepository
) {

    fun execute(pageNumber: Int): Single<HomeworkContent> {
        return repository.getHomeworkList(pageNumber)
    }
}
