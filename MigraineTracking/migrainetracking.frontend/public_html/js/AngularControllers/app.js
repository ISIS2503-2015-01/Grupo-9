/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var app = angular.module('migraineTracking',['registroDoctor']);
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
    
})();



