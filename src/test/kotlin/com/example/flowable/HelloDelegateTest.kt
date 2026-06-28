package com.example.flowable

import org.assertj.core.api.Assertions.assertThat
import org.flowable.cmmn.api.CmmnHistoryService
import org.flowable.cmmn.api.CmmnRuntimeService
import org.junit.jupiter.api.Test

@IntegrationTest
class HelloDelegateTest(
    private val cmmnRuntimeService: CmmnRuntimeService,
    private val cmmnHistoryService: CmmnHistoryService,
) {

    @Test
    fun delegateIsTriggeredAndSetsGreetingVariable() {
        val instance = cmmnRuntimeService.createCaseInstanceBuilder()
            .caseDefinitionKey("helloCase")
            .start()

        val greeting = cmmnHistoryService.createHistoricVariableInstanceQuery()
            .caseInstanceId(instance.id)
            .greeting

        assertThat(greeting).isEqualTo("Hello from delegate!")
    }
}
