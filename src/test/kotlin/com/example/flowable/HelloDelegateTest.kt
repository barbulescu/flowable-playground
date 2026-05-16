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

        val greeting = historyService.createHistoricVariableInstanceQuery()
            .processInstanceId(instance.id)
            .greeting

        assertThat(greeting).isEqualTo("Hello from delegate!")
    }
}
