module.exports = {
    mode: 'jit',
    purge: ['./src/pages/**/*.{js,ts,jsx,tsx}', './src/components/**/*.{js,ts,jsx,tsx}'],
    darkMode: false, // or 'media' or 'class'
    theme: {
        extend: {},
    },
    variants: {
        scrollbar: ["rounded"],
        extend: {},
    },
    plugins: [
        require("tailwind-scrollbar"),
        require("tailwind-scrollbar-hide"),
        require('@tailwindcss/line-clamp'),
    ],
}
