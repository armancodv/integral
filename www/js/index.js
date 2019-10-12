class Model {
    constructor() {
        this.version = '4.0.0';
        this.package = 'com.armanco.integral';
        this.homepage = 'http://arman.co.com/';
        this.api = 'http://arman.co.com/api/applist_formula.php';
        this.addId = 'ca-app-pub-4301546764905932/7472029245';
        this.title = 'Integral';
        this.subTitle = 'Integral Table';
        this.description = 'This app is the list of integrals.';
        this.page = 1;
        this.category = 0;

        this.categoriesTitles = ['Rational', 'Exponential', 'Logarithms', 'Trigonometric', 'Inverse trigonometric', 'Hyperbolic', 'Inverse hyperbolic', 'second derivatives', 'Absolute-value', 'Special', 'Definite integrals'];
        this.categoriesThumbs = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X', 'XI'];
    
        this.categoryTitles = [
            ['k', 'x^a', '(ax+b)^n', '1/x', '1/x general', 'c/(ax)', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21'],
            ['e^(ax)', 'f\'e^f', 'a^x', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
            ['ln(x)', 'log(x)', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26'],
            ['sin(x)', 'cos(x)', 'tan(x)', 'cot(x)', 'sec(x)', 'csc(x)', 'sec2(x)', 'csc2(x)', 'sec(x)tan(x)', 'csc(x)cot(x)', 'sin2(x)', 'cos2(x)', 'sec3(x)', 'sinn(x)', 'cosn(x)', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52', '53', '54', '55', '56', '57', '58', '59', '60', '61', '62', '63', '64'],
            ['arcsin(x)', 'arccos(x)', 'arctan(x)', 'arccot(x)', 'arcsec(x)', 'arccsc(x)', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
            ['sinh(x)', 'cosh(x)', 'tanh(x)', 'coth(x)', 'sech(x)', 'csch(x)', '7', '8', '9', '10', '11', '12', '13', '14', '15'],
            ['arcsinh(x)', 'arccosh(x)', 'arctanh(x)', 'arccoth(x)', 'arcsech(x)', 'arccsch(x)', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18'],
            ['cos(ax)e^(bx)', 'sin(ax)e^(bx)', 'cos(ax)cosh(bx)', 'sin(ax)cosh(bx)'],
            ['|f(x)|', '|(ax+b)^n|', '|tan(ax)|', '|csc(ax)|', '|sec(ax)|', '|cot(ax)|', '|sin(ax)|', '|cos(ax)|'],
            ['Ci', 'Si: Trigonometric integrals', 'Ei: Exponential integral', 'li: Logarithmic integral function', 'li(x)/x', 'erf: Error function'],
            ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26']
        ];
    }

    changeCategory(number) {
        this.category = number;
    }

    changePage(page) {
        this.page = page;
    }

    getApps() {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', this.api + '?p=' + this.package);
        xhr.send();
        xhr.onload = () => {
            this.apps = JSON.parse(xhr.response);
        };

    }


};

class View {
    constructor() {
        this.menu = document.getElementById('menu');
        this.items = document.getElementById('items');
        this.apps = document.getElementById('apps');
        this.categoryTitle = document.getElementById('page2-h1');
        this.title = document.getElementById('title');
        this.subTitle = document.getElementById('subtitle');
        this.description = document.getElementById('description');
        this.armanco = document.getElementById('armanco');
        this.versions = Array.from(document.getElementsByClassName('version'));
        this.backs = Array.from(document.getElementsByClassName('back'));
    }

    openLink(link) {
        window.open(link, '_system');
    }

    changePage(page_number) {
        window.location.hash = '#page' + page_number;
        for (let i = 1; i <= 4; i++) {
            if (i === page_number) {
                document.getElementById('page' + i).style.display = 'block';
            } else {
                document.getElementById('page' + i).style.display = 'none';
            }
        }
        document.body.scrollTop = document.documentElement.scrollTop = 0;
    }

    addMenuItem(id, thumb, title) {
        let element = `<table id="${id}" class="property" cellpadding="0" cellspacing="0"><tr><td class="property-thumb">${thumb}</td><td class="property-name">${title}</td></tr></table>`;
        this.menu.insertAdjacentHTML("beforeend", element);
    }

    addMenuItemMute(id, thumb, title) {
        let element = `<table id="${id}" class="about" cellpadding="0" cellspacing="0"><tr><td class="about-thumb">${thumb}</td><td class="about-name">${title}</td></tr></table>`;
        this.menu.insertAdjacentHTML("beforeend", element);
    }

    addMenuItemApp(item) {
        let element = `<table class="app" cellpadding="0" cellspacing="0" id="app${item.name}"><tr><td class="app-thumb" style="background-image: url('${item.image}')"></td><td class="app-detail"><span class="app-name">${item.name}</span><br><span class="app-price">${item.price}</span><br><span class="app-description">${item.description}</span></td></tr></table>`;
        this.apps.insertAdjacentHTML("beforeend", element);
    }

    addFormula(id, src) {
        let element = `<img src="${src}" style="max-width:100%"><br>`;
        let place = Array.from(document.getElementById(id).getElementsByClassName('identity-formula'));
        place[0].insertAdjacentHTML("beforeend", element);
    }

    addItem(id, title) {
        let element = `<table id="${id}" class="identity" cellpadding="0" cellspacing="0"><tr><td class="identity-title">${title}</td></tr><tr><td class="identity-formula"></td></tr></table>`;
        this.items.insertAdjacentHTML("beforeend", element);
    }

    changeTitle(title) {
        this.title.innerHTML = title;
    }

    changeSubTitle(subTitle) {
        this.subTitle.innerHTML = subTitle;
    }

    changeCategoryTitle(categoryTitle) {
        this.categoryTitle.innerHTML = categoryTitle;
    }

    changeDescription(description) {
        this.description.innerHTML = description;
    }

    changeVersion(version) {
        this.versions.forEach(element => {
            element.innerHTML = version;
        });
    }

    deleteChild(id) {
        let place = document.getElementById(id);
        place.innerHTML = "";
    }

};

class Controller {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.init();
        this.ready();
    }

    init() {
        window.addEventListener("load", () => {
            this.view.changeTitle(this.model.title);
            this.view.changeSubTitle(this.model.subTitle);
            this.view.changeVersion(this.model.version);
            this.view.changeDescription(this.model.description);
            this.model.getApps();
            for (let i = 0; i < this.model.categoriesTitles.length; i++) {
                let thumb = this.model.categoriesThumbs[i];
                let title = this.model.categoriesTitles[i];
                this.addMenuItem(i, thumb, title);
            }
            this.addMenuItemMute('menua', 'apps', 'Similar Apps', 3);
            this.addMenuItemMute('menui', 'info', 'About', 4);
            document.addEventListener("backbutton", e => {
                if (this.model.page !== 1) {
                    e.preventDefault();
                    this.changePage(1);
                } else {
                    navigator.app.exitApp();
                }
            }, false);
            this.view.backs.forEach(element => {
                element.addEventListener("click", () => {
                    this.changePage(1);
                });
            });
            this.view.armanco.addEventListener("click", () => {
                this.view.openLink(this.model.homepage);
            });

        }, false);

    }

    ready() {
        document.addEventListener("deviceready", () => {
            admob.banner.config({
                id: this.model.addId,
                isTesting: false,
                autoShow: true
            });
            admob.banner.prepare();
        }, false);
    }

    changePage(page) {
        this.model.changePage(page);
        this.view.changePage(page);
    }

    async addMenuItem(i, thumb, title) {
        let id = `menu${i}`;
        await this.view.addMenuItem(id, thumb, title);
        document.getElementById(id).addEventListener("click", async () => {
            this.model.changeCategory(i);
            this.view.changeCategoryTitle(title);
            await this.view.deleteChild('items');
            for (let j = 0; j < this.model.categoryTitles[i].length; j++) {
                await this.view.addItem(`item${j}`, this.model.categoryTitles[i][j]);
                this.view.addFormula(`item${j}`, `images\\${i + 1}\\${j + 1}.jpg`);
            }
            this.changePage(2);
        });
    }

    async addMenuItemMute(id, thumb, title, page) {
        await this.view.addMenuItemMute(id, thumb, title);
        document.getElementById(id).addEventListener("click", async () => {
            if (page === 3) {
                await this.view.deleteChild('apps');
                this.model.apps.forEach(async element => {
                    await this.view.addMenuItemApp(element);
                    document.getElementById(`app${element.name}`).addEventListener("click", () => {
                        this.view.openLink(element.url);
                    });
                });
            }
            this.changePage(page);
        });
    }
};

const app = new Controller(new Model(), new View());