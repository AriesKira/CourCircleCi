package fr.gil.exampleAppKotlin

import junit.framework.TestCase.assertTrue
import org.junit.ClassRule
import org.junit.Test


class MainActivityKtTest {
    @Test
    fun verifyPasswordValidity() {
        //setup
        val password = "Wowodzdzadad23&="
        //test
        val errors = _root_ide_package_.fr.gil.exampleAppKotlin.views.verifyPassword(password)
        //result
        assertTrue(errors.isEmpty())
    }
}