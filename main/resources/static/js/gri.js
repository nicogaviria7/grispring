(function($) {
	$(document).ready( function () {
		
		// Ampliar/Reducir la barra lateral
		  $("#sidebarToggle").on('click',function(e) {
		    e.preventDefault();
		    $("body").toggleClass("sidebar-toggled");
		    $(".sidebar").toggleClass("toggled");
		  });

		  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
		  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
		    if ($(window).width() > 768) {
		      var e0 = e.originalEvent,
		      delta = e0.wheelDelta || -e0.detail;
		      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
		      e.preventDefault();
		    }
		  });
		  
		  //Configuración de búsqueda en Datatables.
		  $.fn.DataTable.ext.type.search['string'] = function ( data ) {
		      return ! data ?
		          '' :
		          typeof data === 'string' ?
		              data
		                  .replace( /έ/g, 'ε')
		                  .replace( /ύ/g, 'υ')
		                  .replace( /ό/g, 'ο')
		                  .replace( /ώ/g, 'ω')
		                  .replace( /ά/g, 'α')
		                  .replace( /ί/g, 'ι')
		                  .replace( /ή/g, 'η')
		                  .replace( /\n/g, ' ' )
		                  .replace( /[áÁ]/g, 'a' )
		                  .replace( /[éÉ]/g, 'e' )
		                  .replace( /[íÍ]/g, 'i' )
		                  .replace( /[óÓ]/g, 'o' )
		                  .replace( /[úÚ]/g, 'u' )
		                  .replace( /ê/g, 'e' )
		                  .replace( /î/g, 'i' )
		                  .replace( /ô/g, 'o' )
		                  .replace( /è/g, 'e' )
		                  .replace( /ï/g, 'i' )
		                  .replace( /ü/g, 'u' )
		                  .replace( /ã/g, 'a' )
		                  .replace( /õ/g, 'o' )
		                  .replace( /ç/g, 'c' )
		                  .replace( /ì/g, 'i' ) :
		              data;
		  };
		  
		  //Configuración por defecto de botones e idioma de DataTables.
		  if(document.getElementById('table')) {
			    $.extend( $.fn.dataTable.defaults, {
			      responsive: true,
			      dom: 'Bfrtip',
			      buttons: [
			      {
			        extend: 'copy',
			        text: 'Copiar',
			        className: 'copyButton'
			      },
			      {
			        extend: 'excel',
			        text: 'Excel',
			        className: 'excelButton'
			      },
			      {
			        extend: 'pdf',
			        text: 'PDF',
			        className: 'pdfButton'
			      },
			      {
			        extend: 'print',
			        text: 'Imprimir',
			        className: 'printButton'
			      }
			      ],

			      language: {
			        processing:     "Procesamiento en curso...",
			        search:         "Buscar: ",
			        lengthMenu:    "Mostrando _MENU_ elementos",
			        info:           "Mostrando _START_ a _END_ de _TOTAL_ elementos",
			        infoEmpty:      "Mostrando 0 a 0 de 0 elementos",
			        infoFiltered:   "(filtrado de _MAX_ elementos en total)",
			        infoPostFix:    "",
			        loadingRecords: "Cargando resultados...",
			        zeroRecords:    "No hay información para mostrar",
			        emptyTable:     "No hay información para mostrar",
			        paginate: {
			          first:      "Primera",
			          previous:   "Anterior",
			          next:       "Siguiente",
			          last:       "última"
			        }
			      }
			    } );
			  }
		  
		  var tabla_investigadores= $('#tabla_investigadores').DataTable( {
			  	  responsive: true,
			      rowId: 'id',
			      "order": [[ 1, "asc" ]],
			      columns: [
			      { data: "id" , visible:false},
			      { data: "nombre" } ,
			      { data: "categoria" } ,
			      { data: "nivelAcademico" }
			      ]
			    } );
			  
			  $('#tabla_investigadores_filter input').keyup( function () {  
				  tabla_investigadores
			        .search(
			          jQuery.fn.DataTable.ext.type.search.string( this.value )
			        )
			        .draw(); 
			    } );
			  
			  $('#tabla_investigadores tbody').on('click', 'tr', function () {
				    var data = tabla_investigadores.row( this ).data();
				    window.location.href="general?id="+data.id+"&type=i";
				  } );
			  
			  
			  var tabla_centros= $('#tabla_centros').DataTable( {
				    responsive: true,
				      rowId: 'id',
				      columns: [
				      { data: "id", "visible": false },
				      { data: "nombre" },
				      { data: "facultad.nombre" }
				      ]
				    } );
				  
				  $('#tabla_centros_filter input').keyup( function () {  
					  tabla_centros
				        .search(
				          jQuery.fn.DataTable.ext.type.search.string( this.value )
				        )
				        .draw(); 
				    } ); 
				  
			  $('#tabla_centros tbody').on('click', 'tr', function () {
				    var data = tabla_centros.row( this ).data();
				    window.location.href="general?id="+data.id+"&type=c";
				  });
			  
			  $('#tabla_idiomas').DataTable( {
      		      columns: [
      		      { data: "idioma", className:"font-weight-bold"},
      		      { data: "habla" } ,
      		      { data: "escribe" } ,
      		      { data: "lee" },
      		      { data: "entiende" }
      		      ]
      		    } );
  
			  var tabla_integrantes = $('#tabla_integrantes').DataTable( {
	      	         rowId: 'id',
	      	         columns: [
	      	         { data: "id" , visible:false},
	      	         { data: "nombre" } ,
	      	         { data: "categoria" } ,
	      	         { data: "nivelAcademico" } ,
	      	         { data: "pertenencia" }
	      	         ]
	      	       } );
			  
			  $('#tabla_integrantes tbody').on('click', 'tr', function () {
      		    var data = tabla_integrantes.row( this ).data();
      		    window.location.href="general?id="+data.id+"&type=i";
      		  });
			  
			  $('#tabla_integrantes_filter input').keyup( function () {  
				  tabla_integrantes
			        .search(
			          jQuery.fn.DataTable.ext.type.search.string( this.value )
			        )
			        .draw(); 
			    } ); 
			  
			  var tabla_pertenecientes= $('#tabla_grupos_pertenecientes').DataTable( {
	      	         rowId: 'id',
	      	         columns: [
	      	         { data: "id" , visible:false},
	      	         { data: "nombre" } ,
	      	         { data: "categoria" } ,
	      	         { data: "lider" }
	      	         ]
	      	       });
			  
			  $('#tabla_grupos_pertenecientes tbody').on('click', 'tr', function () {
	      		    var data = tabla_pertenecientes.row( this ).data();
	      		    window.location.href="general?id="+data.id+"&type=g";
	      		  });
			  
			  var table= $('#tabla_reporte').DataTable( {
				 	search: {"bSmart": false},
				      rowId: 'id',
				      "columnDefs": [ {
				             "targets": [ 0 ],
				             "visible": false,
				      }]
				    });
			  
			  $('#tabla_reporte_filter input').keyup( function () {  
				  table
			      .search(
			        jQuery.fn.DataTable.ext.type.search.string( this.value )
			      )
			      .draw(); 
			  } );
		  
	});
})(jQuery); // End of use strict