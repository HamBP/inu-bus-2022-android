package org.algosketch.inubus.global.store

import org.algosketch.inubus.data.repository.Repository

object Store {
    lateinit var repository: Lazy<Repository>
}