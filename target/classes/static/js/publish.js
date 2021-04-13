$(function () {
    $("#publish_btn").click(function () {
        $(this).attr("disabled", "disabled");
        const task_name = $("#taskName").val();
        const task_desc = $("#taskDesc").val();
        const task_deadline = $("#taskDeadline").val();
        let $doc_types = $("input[name='docTypes']:checked");
        let $form_inputs = $("input[name='formInputs']");
        // $('input[name='checkboxName']:checked')+.each
        const doc_types = [];
        const form_inputs = [];
        let i = 0;
        $.each($doc_types, function () {
            doc_types[i++] = $(this).val();
        });
        i = 0;
        $.each($form_inputs, function () {
            form_inputs[i++] = $(this).val();
        });
        // traditional:true  可传递数组
        $.ajax({
            url: "/task/publish",
            type: "post",
            traditional: true,
            data: {
                taskName: task_name,
                taskDesc: task_desc,
                taskDeadline: task_deadline,
                docTypes: doc_types,
                formInputs: form_inputs
            },
            dataType: "json",
            success(data) {
                if (data["code"] === 200){
                    location.href = "/task/collects/page";
                    return;
                }
                alert(data["msg"]);
            }
        })
    });
});