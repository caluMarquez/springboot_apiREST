


//Traer tabla con jQuery 


function getHeaders(){
	
	return{
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token 
		}
					}
					
async function cargarUsuarios(){
	console.log("HOLA")
	//Llamar al back que trae lista de usuarios con Fetch
	const response = await fetch('api/usuarios', {
		
	method : 'GET',
	headers:{
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token 
		}
	});
	
	
	console.log("response",response)
	const data = await response.json();
	
	console.log("Respuesta de la request",data)
	
	let listadoHTML='';
	
	for(let usuario of data){
		
	let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')"><i class="ri-close-circle-fill"></i></a>'
	let telefono = usuario.telefono === null ? '---' : usuario.telefono;
		
	// Crear variable para que el usuario se mapee como cuerpo del body del table
	let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefono+'</td><td>'+botonEliminar+'</td></tr>';
	
	listadoHTML+=usuarioHTML; 
	}
	document.querySelector('#usuariosSelector tbody').outerHTML = listadoHTML;
}


cargarUsuarios();


async function eliminarUsuario(id){
	
	if(!confirm('Desea eliminar usuario?')){
		
		return;
		
	}
	
//Llamar al back que trae lista de usuarios con Fetch
	const response = await fetch('api/usuarios/'+id, {
		
	method : 'DELETE',
	headers:getHeaders()
	});
	
	location.reload();
}

