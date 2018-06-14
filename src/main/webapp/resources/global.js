$(function() {
	$("#forMe").change(function() {
        if(ifCheck("#forMe")) {
        	var sender = $("#sender").val();
            $("#receiver").val(sender);
        } else {
            $("#receiver").val("");
        }
    });
	
	$("#allCheckFile").change(function() {
		var flag = ifCheck(this);
		$(".checkFile").prop("checked", flag);
	});
	
	$(".allCheck").change(function() {
		var flag = ifCheck(this);
		$(".allCheck").prop("checked", flag);
		$(".checkMail").prop("checked", flag);
	});
	
	$("#addFile").click(function() {
		$(".file").last().click();
	});
	
	$("#hiddenFileSpace").on("change", ".file", function() {
		var file = this.files[0];
		appendFileState(file);
		appendFileTag();
	});
	
	$("#fileBot").on("click", ".fileState > .fileCenter", function() {
		$(this).siblings(".fileLeft").prop("checked", function() {
			return !ifCheck(this);
		});
	});
	
	$("#fileBot").on("change", ".fileState > .fileLeft", function() {
		var flag = ifCheck(this);
		if(!flag) {
			$("#allCheckFile").prop("checked", flag);
		}
	});

	$(".delBtn").click(function() {
		removeMailMarking();
		$(".remove").remove();
		if(ifCheck(".allCheck")) {
			$(".checkMail").prop("checked", false);
		}		
	});
	
	$("#delFile").click(function() {
		removeFileMarking();
		$(".remove").remove();
		if(ifCheck("#allCheckFile")) {
			$("#allCheckFile").prop("checked", false);
		}
	});
	
	$(".rollbackBtn").click(function() {
		$(".checkMail").each(function(index) {
			var item = $(".mailItem").eq(index);
			if(item.is(":visible")) {
				if(ifCheck(this)) {
					var MAIL_PK = item.children(".mailPk").val();
					alert(MAIL_PK);
					var url = "/mail_rollback?MAIL_PK=" + MAIL_PK;
					ajax(url);
					item.addClass("remove");
				}
			}
		});
		$(".remove").remove();
		if(ifCheck(".allCheck"))
			$(".allCheck").prop("checked", false);
	});
	
	$("select").change(function() {
		var selectClass = "." + $(this).prop("class");
		var selectValue = $(this).val();
		$(selectClass).val(selectValue);
	});

	$("#searchBtn").click(function() {
	    var str = $("#searchStr").val();
	    $(".mailItem").hide();
	    $(".mailItem > *:contains('" + str + "')").parent().show();
	});
	
	$(".mailItem > a").click(function() {
		var MAIL_PK = $(this).siblings(".mailPk").val();
		var url = "/mail_changeRead?MAIL_PK=" + MAIL_PK + "&UPDATE_VALUE=1";
		ajax(url);
		$(this).parent().css({ "background-color" : "#e7e7e7" });
	});
	
	$(".readMail").change(function() {
		var selected = $(this).val();
		if(selected != "") {
			$(".checkMail").each(function(index) {
				var item = $(".mailItem").eq(index);
				if(item.is(":visible")) {
					if(ifCheck(this)) {
						var MAIL_PK = item.children(".mailPk").val();
						alert(MAIL_PK);
						var url = "/mail_changeRead?MAIL_PK=" + MAIL_PK + "&UPDATE_VALUE=" + selected;
						ajax(url);
						var color = selected == 1 ? "#e7e7e7" : "white";
						item.css({ "background-color" : color });
					}
				}
			});
		}
	});
});

function appendFileState(file) {
	var fileName = file.name;
	var fileSize = getFileSize(file);
	var element = $("#fileBot");
	var checked = ifCheck("#allCheckFile") ? " checked" : "";
	var msg = 
		"<div class='fileState'>" +
			"<input type='checkbox' class='fileLeft checkFile'" + checked + ">" +
			"<b class='fileCenter'>" + fileName + "</b>" +
			"<b class='fileRight'>" + fileSize + "</b>" +
		"</div>";
	appendElement(element, msg);
}

function appendFileTag() {
	var element = $("#hiddenFileSpace");
	var msg = "<input type='file' name='file' class='file'>"
	appendElement(element, msg);
}

function go(action) {
	var formId = $("#container");
	formId.attr("action",action);
	formId.submit();
}

function appendElement(element, msg) {
	element.append(msg);
}

function getFileSize(file) {
	var sizeUnitArray = [ 'B', 'KB', 'MB', 'GB' ];
	var arrayPointer = 0;
	
	for(var fileSize = file.size; fileSize / 1024 > 1; arrayPointer++) {
		fileSize = Math.round((fileSize / 1024) * 100) / 100;
	}
	return fileSize + ' ' + sizeUnitArray[arrayPointer];
}

function removeFileMarking() {
	$(".checkFile").each(function(index) {
		if(ifCheck(this)) {			
			$(".fileState").eq(index).addClass("remove");
			$(".file").eq(index).addClass("remove");
		}
	});
}

function removeMailMarking() {
	$(".checkMail").each(function(index) {
		var item = $(".mailItem").eq(index);
		if(item.is(":visible")) {
			if(ifCheck(this)) {
				var MAIL_PK = $(this).siblings(".mailPk").val();
				alert(MAIL_PK);
				var url = "/mail_delete?MAIL_PK=" + MAIL_PK;
				ajax(url);
				item.addClass("remove");
			}
		}
	});
}

function ifCheck(element) {
	return $(element).prop("checked");
}

function ajax(url) {
	$.ajax({
		url : url,
		type : 'GET'
	});
}