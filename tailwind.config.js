module.exports = {
  content: [
    './src/main/resources/templates/**/*.html',
    './node_modules/flowbite/**/*.js',
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin'),
  ],
};

