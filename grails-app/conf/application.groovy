

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'arquiweb.tp1.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'arquiweb.tp1.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'arquiweb.tp1.auth.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/markers', 		 access: ['permitAll']],
		[pattern: '/categories', 	 access: ['permitAll']],
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/dbconsole/*',	 access: ['permitAll']], /*Dejo esto para poder tocar la BD*/
		[pattern: '/marker/markers', access: ['permitAll']],
		[pattern: '/marker/*', 		 access: ['ROLE_USER','ROLE_ADMIN']],
		[pattern: '/category/*', 	 access: ['ROLE_ADMIN']],
		[pattern: '/extMarker/*', 	 access: ['ROLE_USER','ROLE_ADMIN']],
		[pattern: '/extCategory/*',  access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/assets/**',      filters: 'none'],
		[pattern: '/**/js/**',       filters: 'none'],
		[pattern: '/**/css/**',      filters: 'none'],
		[pattern: '/**/images/**',   filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/markers', 		 filters: 'none'],
		[pattern: '/categories', 	 filters: 'none'],
		[pattern: '/search*', 	 	 filters: 'none'],
		[pattern: '/**',             filters: 'JOINED_FILTERS']
]

