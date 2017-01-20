//=====================================
$(document).ready(function()
	{
	//=================================
	console.log("start test");
	console.log("test GET");
	$.ajax(
		{
		url: '/restql/db',
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
		url: '/restql/db',
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
		url: '/restql/db',
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
		url: '/restql/db',
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
		url: '/restql/db',
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
		url: '/restql/db',
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
