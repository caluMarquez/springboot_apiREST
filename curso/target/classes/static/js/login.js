

async function iniciarSesion(){
	
	let datos = {};
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;

console.log("datos: ", datos)
const request = await fetch('api/login',{
	
	method: 'POST',
	headers:{
		
		'Accept':'application/json',
		'Content-Type':'application/json'
	},
	
	body: JSON.stringify(datos)
	
})

const respuesta = await request.text();

if(respuesta !== "MAL"){
	localStorage.token=respuesta;
	localStorage.email=datos.email;
	window.location.href = 'index.html';
}else{
	window.alert("El mail o la contraseña son incorrectos")
}
}