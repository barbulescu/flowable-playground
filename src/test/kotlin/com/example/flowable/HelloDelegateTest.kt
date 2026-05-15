package com.example.flowable

import org.assertj.core.api.Assertions.assertThat
import org.flowable.engine.HistoryService
import org.flowable.engine.RuntimeService
import org.junit.jupiter.api.Test

@IntegrationTest
class HelloDelegateTest(
    private val runtimeService: RuntimeService,
    private val historyService: HistoryService,
) {

    @Test
    fun delegateIsTriggeredAndSetsGreetingVariable() {
        val instance = runtimeService.startProcessInstanceByKey("helloProcess")

        val variable = historyService.createHistoricVariableInstanceQuery()
            .processInstanceId(instance.id)
            .variableName("greeting")
            .singleResult()

        assertThat(variable)
            .isNotNull()
            .extracting { it.value }
            .isEqualTo("Hello from delegate!")
    }
}
