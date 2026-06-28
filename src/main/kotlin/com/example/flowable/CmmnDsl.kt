package com.example.flowable

import org.flowable.cmmn.model.Case
import org.flowable.cmmn.model.CmmnModel
import org.flowable.cmmn.model.ImplementationType
import org.flowable.cmmn.model.PlanItem
import org.flowable.cmmn.model.ServiceTask
import org.flowable.cmmn.model.ServiceTask.JAVA_TASK
import org.flowable.cmmn.model.Stage

private fun String.toElementId(): String =
    trim().split(Regex("\\s+"))
        .mapIndexed { i, word -> if (i == 0) word.lowercase() else word.lowercase().replaceFirstChar { it.uppercaseChar() } }
        .joinToString("")

fun cmmnModel(targetNamespace: String = "http://flowable.org/cmmn", block: CmmnModel.() -> Unit): CmmnModel {
    val model = CmmnModel()
    model.targetNamespace = targetNamespace
    model.apply(block)
    return model
}

fun CmmnModel.case(name: String, block: Case.() -> Unit) {
    addCase(Case().apply {
        this.id = name.toElementId()
        this.name = name
        block()
    })
}

fun Case.planModel(name: String, block: Stage.() -> Unit) {
    planModel = Stage().apply {
        this.id = name.toElementId()
        this.name = name
        isPlanModel = true
        block()
    }
}

fun Stage.javaServiceTask(name: String, delegateExpression: String) {
    val taskId = name.toElementId()
    addPlanItemDefinition(ServiceTask().apply {
        this.id = taskId
        this.name = name
        type = JAVA_TASK
        implementationType = ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION
        implementation = delegateExpression
    })
    addPlanItem(PlanItem().apply {
        this.id = "planItem_$taskId"
        this.definitionRef = taskId
    })
}
