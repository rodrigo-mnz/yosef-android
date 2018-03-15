package br.com.concrete.yosef.api

import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.*
import br.com.concrete.yosef.api.component.ButtonComponent.Companion.BUTTON_TYPE
import br.com.concrete.yosef.api.component.ElementGroupComponent.Companion.ELEMENT_GROUP
import br.com.concrete.yosef.api.component.RadioButtonComponent.Companion.RADIO_BUTTON
import br.com.concrete.yosef.api.component.RadioGroupButtonComponent.Companion.RADIO_GROUP_BUTTON
import br.com.concrete.yosef.api.component.TextFieldComponent.Companion.TEXT_FIELD
import br.com.concrete.yosef.api.component.TextViewComponent.Companion.TEXT_TYPE
import br.com.concrete.yosef.entity.DynamicComponent
import br.com.concrete.yosef.fromJson
import com.google.gson.Gson


class DynamicViewCreator(private val components: Map<String, Component>, private val gson: Gson) {

    fun createViewFromJson(parent: ViewGroup, json: String, listener: OnActionListener? = null) {
        val parentComponent = gson.fromJson<List<DynamicComponent>>(json)
        parentComponent.forEach { addChildrenRecursively(parent, it, listener) }
    }

    private fun addChildrenRecursively(topLevelViewGroup: ViewGroup,
                                       childComponent: DynamicComponent?,
                                       listener: OnActionListener? = null) {

        if (childComponent == null) return

        if (components[childComponent.type] == null) {
            throw IllegalStateException("There are no components registered " +
                    "in this ViewCreator that can render ${childComponent.type}")
        }

        val component = components[childComponent.type]!!
        val view = component.createView(topLevelViewGroup).apply {
            component.applyProperties(this, childComponent.dynamicProperties, listener)
        }

        childComponent.children?.forEach { addChildrenRecursively(view as ViewGroup, it) }
        topLevelViewGroup.addView(view)
    }

    class Builder {

        private val components: MutableMap<String, Component>
        private var gson: Gson = Gson()

        init {
            components = hashMapOf(TEXT_TYPE to TextViewComponent(),
                    BUTTON_TYPE to ButtonComponent(),
                    TEXT_FIELD to TextFieldComponent(),
                    ELEMENT_GROUP to ElementGroupComponent(),
                    RADIO_BUTTON to RadioButtonComponent(),
                    RADIO_GROUP_BUTTON to RadioGroupButtonComponent()
            )
        }

        fun addComponentFor(type: String, component: Component): Builder {
            components[type] = component
            return this
        }

        fun build(): DynamicViewCreator {
            return DynamicViewCreator(components, gson)
        }
    }
}