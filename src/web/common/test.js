//=====================================
$(document).ready(function()
	{
	//=================================
	console.log("start test");
	console.log("test GET");
	$.ajax(
		{
		url: '/storql/db',
		type: 'GET',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#get1Output').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#get1Output').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("test POST");
	$.ajax(
		{
		url: '/storql/db',
		type: 'POST',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#postOutput').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#postOutput').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("test PUT");
	$.ajax(
		{
		url: '/storql/db',
		type: 'PUT',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#putOutput').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#putOutput').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("test DELETE");
	$.ajax(
		{
		url: '/storql/db',
		type: 'DELETE',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#deleteOutput').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#deleteOutput').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("test GET");
	$.ajax(
		{
		url: '/storql/db',
		type: 'GET',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#get2Output').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#get2Output').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("test OPTIONS");
	$.ajax(
		{
		url: '/storql/db',
		type: 'OPTIONS',
		data:
			{
			'sql':"select * from usr",
			'outputType':'JSON'
			},
		success: function(result)
			{
			$('#optionsOutput').html('<pre> SUCCESS: '+result+'</pre>');
			},
		error: function(result)
			{
			$('#optionsOutput').html('<pre> FAILED: '+result+'</pre>');
			}
		}
	);
	console.log("end test");
	//=================================
	});
//=====================================
