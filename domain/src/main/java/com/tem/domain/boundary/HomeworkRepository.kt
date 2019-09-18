package com.tem.domain.boundary

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader
import com.ufms.mediadorpedagogico.domain.entity.homework.HomeworkContent
import io.reactivex.Single

interface HomeworkRepository {
    fun getHomeworkList(pageNumber: Int): XsiNilLoader.Single<HomeworkContent>
}