<div class="ui modal paperuploadmodal">
  <i class="close icon"></i>
  <div class="header">
   Upload Papers
  </div>
  <div class=" content">
    <form class="ui form" action="/uploadpapers"
			method="post" enctype="multipart/form-data">
			<div class="field">
				<label>Name</label> <input type="text" name="username"
					placeholder="Name">
			</div>
			<div class="field">
				<label>College Name</label> <input type="text" name="collegename"
					placeholder="College Name">
			</div>
			<div class="field">
				<label>Contact No</label> <input type="text" name="contactno"
					placeholder="Contact no">
			</div>
			<div class="field">
				<label>Email Id</label> <input type="text" name="emailid"
					placeholder="Email Id">
			</div>
			<div class="field">
				<label></label> 
				<input type="file" name="file"
					placeholder="upload papers" style="outline: none;">
			</div>
			<input type="hidden" id="paperid" name="paperid"
				value="">

			<button class="ui primary button" type="submit">Submit</button>
		</form>
    
  </div>
 
</div>

<div class="ui modal paperuploadsuccess">
  <i class="close icon"></i>
  <div class="header">
   Your Paper has been successfully submitted !!!
  </div>
  <div class=" content">
   
    <div class="description">
      <div class="ui header">Thank you for Uploading Your paper on SymposiumHub</div>
    </div>
  </div>
  <div class="actions">
   
    <div class="ui positive right labeled icon button">
     Close
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<script>
	$(".paperupload").click(function() {
		$('.paperuploadmodal').modal('show');
		var id = $(this).attr("data-id");
		$('#paperid').val(id);
		
	});
</script>