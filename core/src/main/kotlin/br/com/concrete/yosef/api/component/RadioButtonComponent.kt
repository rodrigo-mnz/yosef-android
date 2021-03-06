package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioButton
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.color.TintColorCommand
import br.com.concrete.yosef.api.property.color.TintColorCommand.Companion.TINT_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand.Companion.MARGIN
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand.Companion.PADDING
import br.com.concrete.yosef.api.property.text.TextColorCommand
import br.com.concrete.yosef.api.property.text.TextColorCommand.Companion.TEXT_COLOR
import br.com.concrete.yosef.api.property.text.TextCommand
import br.com.concrete.yosef.api.property.text.TextCommand.Companion.TEXT
import br.com.concrete.yosef.api.property.text.TextSizeCommand
import br.com.concrete.yosef.api.property.text.TextSizeCommand.Companion.TEXT_SIZE
import br.com.concrete.yosef.entity.DynamicProperty

/**
 *  Class that implements the [Component] interface and creates the component
 *  RadioButton([RadioButton]), applying its properties
 */
class RadioButtonComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val RADIO_BUTTON = "radioButton"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        TEXT to TextCommand(),
        TEXT_COLOR to TextColorCommand(),
        BACKGROUND_COLOR to BackgroundColorCommand(),
        TEXT_SIZE to TextSizeCommand(),
        TINT_COLOR to TintColorCommand(),
        PADDING to PaddingPropertyCommand(),
        MARGIN to MarginPropertyCommand(),
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
        return RadioButton(context).apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
    }
}
