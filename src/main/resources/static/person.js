var persons = []

function findperson (personId) {
  return persons[findpersonKey(personId)];
}

function findpersonKey (personId) {
  for (var key = 0; key < persons.length; key++) {
    if (persons[key].id == personId) {
      return key;
    }
  }
}

var personService = {
  findAll(fn) {
    axios
      .get('/api/v1/person/')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/person/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(person, fn) {
    axios
      .post('/api/v1/person/', person)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, person, fn) {
    axios
      .put('/api/v1/person/' + id, person)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteperson(id, fn) {
    axios
      .delete('/api/v1/person/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#person-list',
  data: function () {
    return {persons: [], searchKey: ''};
  },
  computed: {
    filteredpersons() {
      return this.persons.filter((person) => {
      	return person.name.toUpperCase().indexOf(this.searchKey.toUpperCase()) > -1
      })
    }
  },
  mounted() {
    personService.findAll(r => {this.persons = r.data; persons = r.data})
  }
});

var person = Vue.extend({
  template: '#person',
  data: function () {
    return {person: findperson(this.$route.params.person_id)};
  }
});

var personEdit = Vue.extend({
  template: '#person-edit',
  data: function () {
    return {person: findperson(this.$route.params.person_id)};
  },
  methods: {
    updateperson: function () {
      personService.update(this.person.id, this.person, r => router.push('/'))
    }
  }
});

var personDelete = Vue.extend({
  template: '#person-delete',
  data: function () {
    return {person: findperson(this.$route.params.person_id)};
  },
  methods: {
    deleteperson: function () {
      personService.deleteperson(this.person.id, r => router.push('/'))
    }
  }
});

var Addperson = Vue.extend({
  template: '#add-person',
  data() {
    return {
      person: {}
    }
  },
  methods: {
    createperson() {
      personService.create(this.person, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/person/:person_id', component: person, name: 'person'},
		{path: '/add-person', component: Addperson},
		{path: '/person/:person_id/edit', component: personEdit, name: 'person-edit'},
		{path: '/person/:person_id/delete', component: personDelete, name: 'person-delete'}
	]
});

new Vue({
  router
}).$mount('#app')