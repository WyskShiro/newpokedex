package com.tem.plate.util.extensions

import com.tem.plate.util.viewmodels.Placeholder
import io.reactivex.Observable
import io.reactivex.Single


fun <T> Single<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Single<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}

fun <T> Observable<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Observable<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}
