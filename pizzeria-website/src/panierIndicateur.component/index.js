import template from './panierIndicateur.html';

class controller {
  constructor( PanierService ) {
    this.ps = PanierService;
  }

};

export const PanierIndicateurComponent = {
  bindings: {},
  controller,
  template
};
