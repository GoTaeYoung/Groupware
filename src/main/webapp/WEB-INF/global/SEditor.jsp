<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resources/SEditor/js/HuskyEZCreator.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	var editor_object = [];
	var textAreaName = document.URL.substring(document.URL.lastIndexOf("/") + 1,
			document.URL.length);

	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : editor_object,
			elPlaceHolder : "SEditor",
			sSkinURI : "/resources/SEditor/SmartEditor2Skin.html?textAreaName=" + textAreaName,
			htParams : {
				bUseToolbar : true,
				bUseVerticalResizer : false,
				bUseModeChanger : true
			},
			fOnAppLoad : function() {
				editor_object.getById["SEditor"].exec("PASTE_HTML", [""]);
			},
			fCreator : "createSEditor2"
		});
		
		$("#container").submit(function () {
			editor_object.getById["SEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		})
	});
</script>
<textarea rows="15" cols="180" id="SEditor" name="SEditor"></textarea>