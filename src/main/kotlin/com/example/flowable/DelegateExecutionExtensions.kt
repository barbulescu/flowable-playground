package com.example.flowable

import org.flowable.engine.delegate.DelegateExecution
import org.flowable.variable.api.history.HistoricVariableInstanceQuery

var DelegateExecution.greeting: String
    get() = getVariable("greeting") as String
    set(value) = setVariable("greeting", value)

val HistoricVariableInstanceQuery.greeting: String?
    get() = variableName("greeting").singleResult()?.value as? String
