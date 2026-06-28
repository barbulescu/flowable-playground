package com.example.flowable

import org.flowable.cmmn.api.delegate.DelegatePlanItemInstance
import org.flowable.cmmn.api.delegate.PlanItemJavaDelegate
import org.springframework.stereotype.Component

@Component
class HelloDelegate : PlanItemJavaDelegate {

    override fun execute(planItemInstance: DelegatePlanItemInstance) {
        planItemInstance.greeting = "Hello from delegate!"
    }
}
