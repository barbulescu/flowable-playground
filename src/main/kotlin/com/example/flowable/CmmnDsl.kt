package com.example.flowable

import org.flowable.cmmn.model.Case
import org.flowable.cmmn.model.CmmnModel
import org.flowable.cmmn.model.ImplementationType
import org.flowable.cmmn.model.PlanItem
import org.flowable.cmmn.model.ServiceTask
import org.flowable.cmmn.model.ServiceTask.JAVA_TASK
import org.flowable.cmmn.model.Stage

fun cmmnModel(targetNamespace: String = "http://flowable.org/cmmn",block: CmmnModel.() -> Unit): CmmnModel {
    val model = CmmnModel()
    model.targetNamespace = targetNamespace
    model.apply(block)
    return model
}

fun CmmnModel.case(id: String, name: String, block: Case.() -> Unit) {
    addCase(Case().apply {
        this.id = id
        this.name = name
        block()
    })
}

fun Case.planModel(id: String, name: String, block: Stage.() -> Unit) {
    planModel = Stage().apply {
        this.id = id
        this.name = name
        isPlanModel = true
        block()
    }
}

fun Stage.planItem(id: String, definitionRef: String) {
    addPlanItem(PlanItem().apply {
        this.id = id
        this.definitionRef = definitionRef
    })
}

fun Stage.javaServiceTask(id: String, name: String, delegateExpression: String) {
    addPlanItemDefinition(ServiceTask().apply {
        this.id = id
        this.name = name
        type = JAVA_TASK
        implementationType = ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION
        implementation = delegateExpression
    })
}
