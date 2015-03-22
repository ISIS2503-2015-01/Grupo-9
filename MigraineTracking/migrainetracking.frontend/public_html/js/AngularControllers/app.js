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
    app.directive('formDoctorDetalle',function(){
       return{
           restrict:'E',
           templateUrl:'partials/doctor/form-doctordetalle.html',
           controller:function(){
               this.tab2=0;
               this.selectTab2=function(setTab2){
                   this.tab2=setTab2;
               };
               this.isSelected2=function(tabParam2){
                    return this.tab2===tabParam2;
                };
           },
           contollerAs:'formDoctorDetalle'
       }; 
    });
})();



