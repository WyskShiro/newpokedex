package com.ufms.mediadorpedagogico.domain.interactor.notice

import com.ufms.mediadorpedagogico.domain.boundary.NoticeRepository
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import io.reactivex.Single

class GetExample constructor(
    private val repository: ExampleRepository
) {

    fun execute(): Single<NoticeContent> {
        return repository.getSomething(pageNumber)
    }
}
