package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand.Companion.PADDING
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * RadioGroup([RadioGroup]), applying its properties
 */
class RadioGroupButtonComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val RADIO_GROUP_BUTTON = "radioGroupButton"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        BACKGROUND_COLOR to BackgroundColorCommand(),
        PADDING to PaddingPropertyCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return RadioGroup(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
    }
}
