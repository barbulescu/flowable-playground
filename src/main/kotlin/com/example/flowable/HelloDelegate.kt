package com.example.flowable

import org.flowable.engine.delegate.DelegateExecution
import org.flowable.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("helloDelegate")
class HelloDelegate : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        execution.greeting = "Hello from delegate!"
    }
}
