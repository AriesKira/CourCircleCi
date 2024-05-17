package fr.gil.exampleAppKotlin

import junit.framework.TestCase.assertTrue
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