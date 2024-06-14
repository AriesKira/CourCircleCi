package fr.gil.exampleAppKotlin

import fr.gil.exampleAppKotlin.views.verifyPassword
import junit.framework.TestCase.assertTrue
import org.junit.ClassRule
import org.junit.Test


class MainActivityKtTest {
    @Test
    fun verifyPasswordValidity() {
        //setup
        val password = "Wowodzdzadad23&="
        //test
        val errors = verifyPassword(password)
        //result
        assertTrue(errors.isEmpty())
    }
}