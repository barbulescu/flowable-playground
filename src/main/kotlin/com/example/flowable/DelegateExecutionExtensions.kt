package com.example.flowable

import org.flowable.engine.delegate.DelegateExecution

var DelegateExecution.greeting: String
    get() = getVariable("greeting") as String
    set(value) = setVariable("greeting", value)
