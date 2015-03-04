module.exports = function(grunt) {
  'use strict';

var Mustache = require('mustache');

//------------------------------------------------------------------------------
// Built Project Pages
//------------------------------------------------------------------------------

function isSectionLine(line) {
  return (line.search(/^=====/) !== -1);
}

function parseProjectFile(fileContent, filename) {
  var contentArr = fileContent.split("\n"),
    project = {},
    currentSection = false;

  for (var i = 0; i < contentArr.length; i++) {
    var line = contentArr[i];

    if (isSectionLine(line) === true) {
      currentSection = line.replace(/^=====/, "").trim().toLowerCase();
      project[currentSection] = "";
      continue;
    }

    if (currentSection === false) continue;

    project[currentSection] += line + "\n";
  }

  for (var i in project) {
    if (project.hasOwnProperty(i) !== true) continue;

    project[i] = project[i].trim();
  }

  // use the filename as an id
  project["id"] = filename.replace(/\.project$/, "");

  return project;
}

var PROJECT_TEMPLATE = grunt.file.read('templates/projects.mustache'),
    WORKS_PROJECT_TEMPLATE = grunt.file.read('templates/works.mustache');

function createProjectHTMLFile(file){
  var filename = 'public/projects/' + file.id + '.html';

  var template = PROJECT_TEMPLATE;
  if (file.id.charAt(0) === "1") {
    template = WORKS_PROJECT_TEMPLATE;
  }

  var html = Mustache.render(template, file);

  grunt.file.write(filename, html);
}

function createProjectHTMLPages(projects){
  // remove any existing files
  grunt.file.delete('public/projects');
  grunt.file.mkdir('public/projects');

  projects.forEach(createProjectHTMLFile);
}

function buildProjects(){
  var projects = [];

  grunt.file.recurse("projects", function(abspath, rootdir, subdir, filename) {
    var fileContent = grunt.file.read(abspath);

    projects.push(parseProjectFile(fileContent, filename));
  });

  // create projects.json
  grunt.file.write("public/projects.json", JSON.stringify(projects));

  // create project files
  createProjectHTMLPages(projects);
}


//------------------------------------------------------------------------------
// Grunt Config
//------------------------------------------------------------------------------

grunt.initConfig({

  // LESS conversion
  less: {
    options: {
      compress: true
    },

    default: {
      files: {
        'public/css/main.min.css': 'less/main.less'
      }
    }
  },

  // watch
  watch: {
    options: {
      atBegin: true
    },

    less: {
      files: "less/*.less",
      tasks: "less:default"
    },

    projects: {
      files: "projects/*.project",
      tasks: "projects"
    }
  }

});

//------------------------------------------------------------------------------
// Load Tasks
//------------------------------------------------------------------------------

// load tasks from npm
grunt.loadNpmTasks('grunt-contrib-less');
grunt.loadNpmTasks('grunt-contrib-watch');

grunt.registerTask('projects', buildProjects);
grunt.registerTask('default', ['watch']);

//end module.exports
};