package model.service;

import model.factory.*;
import model.repository.TrabajoRepository;

public class TrabajoSeeder {

    private final TrabajoRepository trabajoRepository;

    public TrabajoSeeder(TrabajoRepository trabajoRepository) {
        this.trabajoRepository = trabajoRepository;
    }

    public void cargarDatosIniciales(){
        TrabajoFactory construccionFactory = new TrabajoConstruccionFactory();
        TrabajoFactory ventasFactory = new TrabajoVentasFactory();
        TrabajoFactory transporteFactory = new TrabajoTransporteFactory();
        TrabajoFactory mudanzaFactory = new TrabajoMudanzaFactory();

        trabajoRepository.guardar(construccionFactory.crearTrabajo("Ayudante de obra", "Asistencia en obras civiles", "1 año", 950)); // bajo
        trabajoRepository.guardar(construccionFactory.crearTrabajo("Albañil calificado", "Construcción de estructuras", "2 - 5 años", 1800)); // medio
        trabajoRepository.guardar(construccionFactory.crearTrabajo("Jefe de obra", "Supervisión de grandes proyectos", "6 años a más", 3500)); // alto
        trabajoRepository.guardar(construccionFactory.crearTrabajo("Encofrador", "Montaje de moldes para estructuras", "2 - 5 años", 1900)); // medio
        trabajoRepository.guardar(construccionFactory.crearTrabajo("Maestro de acabados", "Acabados finos en interiores", "6 años a más", 3100)); // alto

        trabajoRepository.guardar(ventasFactory.crearTrabajo("Vendedor ambulante", "Venta de productos en la calle", "1 año", 1000)); // bajo
        trabajoRepository.guardar(ventasFactory.crearTrabajo("Asesor comercial", "Venta en tienda y seguimiento de clientes", "2 - 5 años", 1600)); // medio
        trabajoRepository.guardar(ventasFactory.crearTrabajo("Gerente de ventas", "Gestión de equipo comercial", "6 años a más", 3200)); // alto
        trabajoRepository.guardar(ventasFactory.crearTrabajo("Promotor de ventas", "Promoción de productos en campo", "1 año", 1050)); // bajo
        trabajoRepository.guardar(ventasFactory.crearTrabajo("Supervisor de ventas", "Supervisión y entrenamiento del equipo", "6 años a más", 3100)); // alto

        trabajoRepository.guardar(transporteFactory.crearTrabajo("Conductor de mototaxi", "Transporte urbano en zonas locales", "1 año", 1100)); // bajo
        trabajoRepository.guardar(transporteFactory.crearTrabajo("Repartidor de mercadería", "Distribución a nivel ciudad", "2 - 5 años", 1900)); // medio
        trabajoRepository.guardar(transporteFactory.crearTrabajo("Chofer de camión articulado", "Transporte interprovincial", "6 años a más", 4000)); // alto
        trabajoRepository.guardar(transporteFactory.crearTrabajo("Conductor de combi", "Transporte urbano de pasajeros", "2 - 5 años", 1850)); // medio
        trabajoRepository.guardar(transporteFactory.crearTrabajo("Chofer ejecutivo", "Conducción para empresa privada", "6 años a más", 3200)); // alto

        trabajoRepository.guardar(mudanzaFactory.crearTrabajo("Ayudante de mudanza", "Carga y descarga de muebles", "1 año", 1000)); // bajo
        trabajoRepository.guardar(mudanzaFactory.crearTrabajo("Coordinador de logística", "Organización de servicios de mudanza", "2 - 5 años", 1750)); // medio
        trabajoRepository.guardar(mudanzaFactory.crearTrabajo("Supervisor de operaciones", "Control de operaciones y logística", "6 años a más", 3300)); // alto
        trabajoRepository.guardar(mudanzaFactory.crearTrabajo("Empaquetador profesional", "Protección y embalaje de artículos", "2 - 5 años", 1600)); // medio
        trabajoRepository.guardar(mudanzaFactory.crearTrabajo("Jefe de cuadrilla", "Liderar grupo de cargadores", "6 años a más", 3500)); //alto
    }

}
