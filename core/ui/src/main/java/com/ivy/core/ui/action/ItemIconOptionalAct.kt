package com.ivy.core.ui.action

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.DrawableRes
import com.ivy.core.domain.action.Action
import com.ivy.core.ui.data.icon.IconSize
import com.ivy.core.ui.data.icon.ItemIcon
import com.ivy.data.ItemIconId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ItemIconOptionalAct @Inject constructor(
    @ApplicationContext
    private val appContext: Context
) : Action<ItemIconId, ItemIcon?>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun action(iconId: ItemIconId): ItemIcon? {
        fun unknown(): ItemIcon? =
            getIcon(iconId = iconId)?.let { iconRes ->
                ItemIcon.Unknown(
                    icon = iconRes,
                    iconId = iconId,
                )
            }

        val iconS = getSizedIcon(iconId = iconId, size = IconSize.S) ?: return unknown()
        val iconM = getSizedIcon(iconId = iconId, size = IconSize.M) ?: return unknown()
        val iconL = getSizedIcon(iconId = iconId, size = IconSize.L) ?: return unknown()

        return ItemIcon.Sized(
            iconS = iconS,
            iconM = iconM,
            iconL = iconL,
            iconId = iconId,
        )
    }

    @DrawableRes
    fun getSizedIcon(
        iconId: ItemIconId?,
        size: IconSize,
    ): Int? = iconId?.let {
        getDrawableByName(
            fileName = "ic_custom_${normalize(iconId)}_${size.value}"
        )
    }

    @DrawableRes
    private fun getIcon(
        iconId: ItemIconId?
    ): Int? = iconId?.let {
        getDrawableByName(
            fileName = normalize(iconId)
        )
    }

    @SuppressLint("DiscouragedApi")
    @DrawableRes
    private fun getDrawableByName(fileName: String): Int? = try {
        appContext.resources.getIdentifier(
            fileName,
            "drawable",
            appContext.packageName
        ).takeIf { it != 0 }
    } catch (e: Exception) {
        null
    }

    private fun normalize(iconId: ItemIconId): String = iconId
        .replace(" ", "")
        .trim()
        .lowercase()
}