package com.example.flowable

import org.flowable.cmmn.api.delegate.DelegatePlanItemInstance
import org.flowable.cmmn.api.history.HistoricVariableInstanceQuery

var DelegatePlanItemInstance.greeting: String
    get() = getVariable("greeting") as String
    set(value) = setVariable("greeting", value)

val HistoricVariableInstanceQuery.greeting: String?
    get() = variableName("greeting").singleResult()?.value as? String
