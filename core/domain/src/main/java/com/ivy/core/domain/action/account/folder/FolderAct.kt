package com.ivy.core.domain.action.account.folder

import com.ivy.common.time.provider.TimeProvider
import com.ivy.common.time.toLocal
import com.ivy.core.domain.action.Action
import com.ivy.core.persistence.dao.account.AccountFolderDao
import com.ivy.core.persistence.entity.account.AccountFolderEntity
import com.ivy.data.Sync
import com.ivy.data.account.Folder
import javax.inject.Inject

class FolderAct @Inject constructor(
    private val accountFolderDao: AccountFolderDao,
    private val timeProvider: TimeProvider,
) : Action<String, Folder?>() {
    override suspend fun action(folderId: String): Folder? =
        accountFolderDao.findById(folderId)?.let {
            toDomain(it, timeProvider)
        }
}

fun toDomain(
    entity: AccountFolderEntity,
    timeProvider: TimeProvider,
) = Folder(
    id = entity.id,
    name = entity.name,
    icon = entity.icon,
    color = entity.color,
    orderNum = entity.orderNum,
    sync = Sync(
        state = entity.sync,
        lastUpdated = entity.lastUpdated.toLocal(timeProvider)
    ),
)