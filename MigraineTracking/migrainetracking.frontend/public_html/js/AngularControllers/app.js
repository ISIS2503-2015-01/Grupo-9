/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var app = angular.module('migraineTracking',['doctorDirectives','episodioDirectives', 'pacienteDirectives']);
    app.directive('navbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/navbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'navbar'
        };
    });
    app.directive('doctorSesion', function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-sesion.html',
            controller: ['$http',function($http){
                var self=this;
                self.user={};
                console.log(JSON.stringify(self.user));
                this.iniciarSesion=function(){
                    $http.post('http://migraine-services.herokuapp.com/webresources/auth/login', JSON.stringify(self.user)).success(function(data){
                        self.user={};
                        alert(data);
                        alert("Inicio sesion correctamente");
                        localStorage.setItem("token",data);
                        console.log(data);
                    }).error(function(data){
                        alert("Hubo un error iniciando la sesion");
                    });
                };
            }],
                controllerAs:'inicio'
        };
    });
    function hash_message( data ){
	var hash  = CryptoJS.SHA512( data ) ;
	var string_bytes  = CryptoJS.enc.Base64.stringify(hash);
	console.log( string_bytes );
	return string_bytes;
    };
		
    //Integrity verification for Sha512
    function verifyIntegrity( data , data_hash ){
        var new_message_hash = hash_message( data );
        if(new_message_hash === data_hash)
                return true;
        else
                return false;
    };
})();



