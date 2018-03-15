package br.com.concrete.yosef.ui.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.KeyEvent
import android.widget.EditText
import br.com.concrete.yosef.activities.MainActivity
import br.com.concrete.yosef.test.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextFieldComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun whenAddTextFieldReplaceTextThenShowTextView() {

        onView(allOf(
                isAssignableFrom(EditText::class.java)))
                .perform(typeText("Teste de textfield"), ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        onView(ViewMatchers.withId(R.id.textview))
                .check(ViewAssertions.matches(ViewMatchers.withText("Teste de textfield")))
    }

    @Test
    fun wheAddTextWithMonetaryMaskThenShowValueFormatted() {
        onView(allOf(
                isAssignableFrom(EditText::class.java)))
                .perform(typeText("123123123"))

        onView(ViewMatchers.withText("R$ 1.231.231,23"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}